package pl.complaints.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.complaints.dao.Complaint;
import pl.complaints.dto.complaint.ComplaintResponseDTO;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {

    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    @Mapping(source = "product.name", target = "productName")
    ComplaintResponseDTO toDto(Complaint complaint);
}