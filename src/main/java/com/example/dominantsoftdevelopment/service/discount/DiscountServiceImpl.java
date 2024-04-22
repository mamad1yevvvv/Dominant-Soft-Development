package com.example.dominantsoftdevelopment.service.discount;

import com.example.dominantsoftdevelopment.dto.AddDiscountDTO;
import com.example.dominantsoftdevelopment.dto.ApiResult;
import com.example.dominantsoftdevelopment.dto.DiscountDTO;
import com.example.dominantsoftdevelopment.dto.ProductDTO;
import com.example.dominantsoftdevelopment.exceptions.RestException;
import com.example.dominantsoftdevelopment.model.Discount;
import com.example.dominantsoftdevelopment.model.Product;
import com.example.dominantsoftdevelopment.notification.NotificationMessagingService;
import com.example.dominantsoftdevelopment.repository.DiscountRepository;
import com.example.dominantsoftdevelopment.repository.ProductRepository;
import com.example.dominantsoftdevelopment.repository.UserRepository;
import com.example.dominantsoftdevelopment.rsql.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final NotificationMessagingService messagingService;
    @Override
    public ApiResult<Boolean> createDiscount(AddDiscountDTO discountDTO) {
        Long productId = discountDTO.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> RestException.restThrow("product not found"));

        LocalDateTime finishedAt = discountDTO.getFinishedAt();
        checkDate(finishedAt);

        Discount existDiscount = discountRepository
                .findLastDiscountByProductId(product.getId()).orElse(null);
        if (existDiscount != null) {
            existDiscount.setIsActive(false);
            discountRepository.save(existDiscount);
        }

        // todo
      /*  List<User> allUser = userRepository.findAll();
        Map<String , String>  map= new HashMap<>();
        map.put("" , "");
        if (allUser != null){
            for (User user : allUser) {
                messagingService.sendWebNotificationByToken(new NotificationMessageDto(user.getFirebaseToken(),"" , "" , "" , map));
            }
        }
*/
        Discount discount = Discount.builder()
                    .productId(product)
                    .isActive(discountDTO.getIsActive())
                    .percentage(discountDTO.getPercentage())
                    .finishedAt(finishedAt)
                    .build();

        discountRepository.save(discount);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<DiscountDTO> getDiscount(Long discountId) {
        Discount discount = get(discountId);
        DiscountDTO discountDTO = DiscountDTO.builder()
                .id(discount.getId())
                .createdAt(discount.getCreatedAt())
                .updatedAt(discount.getUpdatedAt())
                .isActive(discount.getIsActive())
                .percentage(discount.getPercentage())
                .finishedAt(discount.getFinishedAt())
                .productId(mapper.map(discount.getProductId(), ProductDTO.class))
                .build();
        return ApiResult.successResponse(discountDTO);
    }

    @Override
    public ApiResult<Page<DiscountDTO>> getAll(Pageable pageable, String predicate) {
        Specification<Discount> specification = SpecificationBuilder.build( predicate );
        if( specification == null ) {
             return ApiResult.successResponse(discountRepository.findAll(pageable)
                     .map(discount -> mapper.map(discount, DiscountDTO.class)));
        }
        return ApiResult.successResponse(discountRepository.findAll( specification, pageable )
                .map( discount -> mapper.map(discount, DiscountDTO.class)));
    }

    @Override
    public ApiResult<Boolean> updateDiscount(Long discountId, AddDiscountDTO discountDTO) {
        Discount discount = get(discountId);
        checkDate(discount.getFinishedAt());

        mapper.map(discountDTO, discount);
        discountRepository.save(discount);
        return ApiResult.successResponse(true);
    }

    @Override
    public void deleteDiscount(Long discountId) {
        Discount discount = get(discountId);
        discountRepository.deleteById(discountId);
    }

    public Discount get(Long id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("product not found"));
    }

    private void checkDate(LocalDateTime finishedAt) {
        if (finishedAt.isBefore(LocalDateTime.now())) {
            throw RestException.restThrow("Discount finishing date cannot be before current time : %s".formatted(LocalDateTime.now()));
        }
    }
}
