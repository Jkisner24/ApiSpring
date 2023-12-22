package com.exampleRest.controller;

import com.exampleRest.dto.OwnerDTO;
import com.exampleRest.entities.Owner;
import com.exampleRest.exception.ExpenseNotFound;
import com.exampleRest.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping()
    public ResponseEntity<?> findAll(){
        try{
        List<OwnerDTO> ownerDTOList = ownerService.findAll()
                .stream()
                .map(owner -> OwnerDTO.builder()
                        .id(owner.getId())
                        .name(owner.getName())
                        .expenseList(owner.getExpenseList())
                        .build())
                .toList();
            return ResponseEntity.ok(ownerDTOList);
    }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            Optional<Owner> ownerOptional = ownerService.findById(id);

            if(ownerOptional.isPresent()){
                Owner owner = ownerOptional.get();
                OwnerDTO ownerDTO = OwnerDTO.builder()
                    .id(owner.getId())
                    .name(owner.getName())
                    .expenseList(owner.getExpenseList())
                    .build();
                return ResponseEntity.ok(ownerDTO);
            }
                throw new ExpenseNotFound(id);
        }catch(Exception e){
            throw e;
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody OwnerDTO ownerDTO) throws URISyntaxException {
        try{
            if(ownerDTO.getName().isBlank() || ownerDTO.getExpenseList() == null){
                return ResponseEntity.badRequest().build();
            }
            Owner owner = Owner.builder()
                    .name(ownerDTO.getName())
                    .expenseList(ownerDTO.getExpenseList())
                    .build();
            ownerService.save(owner);
            return ResponseEntity.created(new URI("/api/v1/owner/save")).build();
        }catch (Exception e){
            throw e;
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OwnerDTO ownerDTO){
        try{
            Optional<Owner> ownerOptional = ownerService.findById(id);

            if(ownerOptional.isPresent()){
                Owner owner = ownerOptional.get();
                owner.setName(ownerDTO.getName());
                owner.setExpenseList(ownerDTO.getExpenseList());
                ownerService.save(owner);
                return ResponseEntity.ok("Register updated");
            }
                throw new ExpenseNotFound(id);
        }catch (Exception e){
            throw e;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try{
            if (id != null){
                ownerService.deleteById(id);
                return ResponseEntity.ok("Register deleted");
            }
                throw new ExpenseNotFound(null);
        }catch (Exception e){
            throw e;
        }
    }
}
