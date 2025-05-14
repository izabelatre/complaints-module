package pl.complaints.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.complaints.dao.Complaint;
import pl.complaints.dto.complaint.ComplaintResponseDTO;
import pl.complaints.dto.complaint.ComplaintUpdateRequestDTO;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {

    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    @Mapping(source = "product.name", target = "productName")
    ComplaintResponseDTO toDto(Complaint complaint);

    @Mapping(source = "complaint.id", target = "id")
    @Mapping(source = "complaintUpdateRequestDTO.description", target = "description")
    @Mapping(source = "complaint.createdAt", target = "createdAt")
    @Mapping(source = "complaint.country", target = "country")
    @Mapping(source = "complaint.complaintCounter", target = "complaintCounter")
    @Mapping(source = "complaint.customer", target = "customer")
    @Mapping(source = "complaint.product", target = "product")
    Complaint update(Complaint complaint, ComplaintUpdateRequestDTO complaintUpdateRequestDTO);
}