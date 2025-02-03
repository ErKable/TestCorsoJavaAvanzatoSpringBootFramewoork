package it.cgmconsulting.capriello.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CustomerStoreResponse {
    private String store_name;
    private long total_customer;
}
