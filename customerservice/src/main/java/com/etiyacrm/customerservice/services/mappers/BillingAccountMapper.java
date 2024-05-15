package com.etiyacrm.customerservice.services.mappers;

import com.etiyacrm.customerservice.entities.BillingAccount;
import com.etiyacrm.customerservice.services.dtos.requests.accountRequests.CreateBillingAccountRequest;
import com.etiyacrm.customerservice.services.dtos.requests.accountRequests.UpdateBillingAccountRequest;
import com.etiyacrm.customerservice.services.dtos.responses.accountResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillingAccountMapper {
    BillingAccountMapper INSTANCE = Mappers.getMapper(BillingAccountMapper.class);
    GetAllBillingAccountResponse getAllBillingAccountResponse(BillingAccount billingAccount);

    BillingAccount billingAccountFromCreateBillingAccountRequest(CreateBillingAccountRequest createBillingAccountRequest);

    CreatedBillingAccountResponse createdBillingAccountResponseFromBillingAccount(BillingAccount billingAccount);

    BillingAccount billingAccountFromUpdateBillingAccountRequest(UpdateBillingAccountRequest updateBillingAccountRequest);

    UpdatedBillingAccountResponse updatedBillingAccountResponseFromBillingAccount(BillingAccount billingAccount);

    DeletedBillingAccountResponse deletedBillingAccountResponseFromBillingAccount(BillingAccount billingAccount);

    GetBillingAccountResponse getBillingAccountResponseFromBillingAccount(BillingAccount billingAccount);


}
