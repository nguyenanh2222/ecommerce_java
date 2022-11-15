package com.example.restservice.configuration;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
//        setSkipNullEnabled() có cho phép bỏ qua thuộc tính null hay không
//        setDeepCopyEnabled() mặc định dùng shallow copy, dùng deep copy thì sẽ chậm hơn.
//        setMatchingStrategy() đặt loại mapping (như phần trên)
//        setFieldAccessLevel() chỉ định field truy cập ở mức độ nào (private, public,...)
//        setMethodAccessLevel() chỉ định mức độ truy cập method, getter và setter (như trên)
//        setSourceNameTokenizer() chỉ định quy ước đặt tên cho thuộc tính ở source (object nguồn dùng để map)
//        setDestinationNameTokenizer() chỉ định quy ước đặt tên cho thuộc tính ở đích (object map ra).
    }
}
