package com.online.shop.ecommerceshop.mapper;

import com.online.shop.ecommerceshop.domain.Picture;
import com.online.shop.ecommerceshop.domain.Product;
import com.online.shop.ecommerceshop.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Mapping(target = "pictureUrls", expression = "java(mapPicturesToUrls(product))")
    public abstract ProductDto mapToDto(Product product);

    public List<String> mapPicturesToUrls(Product product){
        return product.getPictures().stream().map(Picture::getUrl).collect(Collectors.toList());
    }
}
