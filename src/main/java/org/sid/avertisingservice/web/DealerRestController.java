package org.sid.avertisingservice.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;
import org.sid.avertisingservice.dtos.DealerDto;
import org.sid.avertisingservice.exceptions.DealerNotFoundException;
import org.sid.avertisingservice.service.DealerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class DealerRestController {

    private DealerService dealerService;

    public DealerRestController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @GetMapping(value = "/dealers")
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<DealerDto>> dealers() {

        return ResponseEntity.ok(dealerService.getAllDealer());
    }

    @GetMapping("/dealers/{dealerId}")
    public ResponseEntity<DealerDto> getDealer(@PathVariable(name = "dealerId") String dealerId) throws DealerNotFoundException {

        return ResponseEntity.ok(dealerService.getDealerById(dealerId));
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/dealers",consumes = "application/json",produces = "application/json")
    public ResponseEntity<DealerDto> saveDealer( @RequestBody DealerDto dealerDto) {

        return ResponseEntity.ok(dealerService.createDealer(dealerDto));
    }

    @PutMapping(value = "/dealers/{dealerId}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<DealerDto> saveDealer(@PathVariable(name = "dealerId") String dealerId, @RequestBody DealerDto dealerDto) {

        dealerDto.setId(dealerId);
        return ResponseEntity.ok(dealerService.updateDealer(dealerDto));
    }

    @DeleteMapping("/dealers/{dealerId}")
    public void deleteDealer(@PathVariable(name = "dealerId") String dealerId)  {

        dealerService.deleteDealerById(dealerId);
    }

}
