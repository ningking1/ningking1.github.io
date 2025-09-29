package com.wms.controller;

import com.wms.entity.Inventory;
import com.wms.entity.PageResult;
import com.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:3002") // 允许前端Vue应用访问
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * 获取所有库存记录
     */
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    /**
     * 根据ID获取库存记录
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        if (inventory != null) {
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 根据物料编码获取库存记录
     */
    @GetMapping("/material/{code}")
    public ResponseEntity<List<Inventory>> getInventoriesByMaterialCode(@PathVariable String code) {
        List<Inventory> inventories = inventoryService.getInventoriesByMaterialCode(code);
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    /**
     * 生成库存二维码
     */
    @GetMapping("/qrcode/{id}")
    public ResponseEntity<Map<String, String>> generateInventoryQrCode(@PathVariable Long id) {
        try {
            String qrCodeBase64 = inventoryService.generateInventoryQrCode(id);
            Map<String, String> response = new HashMap<>();
            response.put("qrCode", qrCodeBase64);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 分页获取所有库存记录
     */
    @GetMapping("/page")
    public ResponseEntity<PageResult<Inventory>> getInventoryByPage(
            @RequestParam(defaultValue = "1") int pageNum, 
            @RequestParam(defaultValue = "10") int pageSize) {
        PageResult<Inventory> pageResult = inventoryService.getInventoryByPage(pageNum, pageSize);
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }
    
    /**
     * 根据物料编码分页获取库存记录
     */
    @GetMapping("/material/{code}/page")
    public ResponseEntity<PageResult<Inventory>> getInventoryByMaterialCodePage(
            @PathVariable String code, 
            @RequestParam(defaultValue = "1") int pageNum, 
            @RequestParam(defaultValue = "10") int pageSize) {
        PageResult<Inventory> pageResult = inventoryService.getInventoryByMaterialCodePage(code, pageNum, pageSize);
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }
    
    /**
     * 多条件分页搜索库存记录
     */
    @GetMapping("/search/page")
    public ResponseEntity<PageResult<Inventory>> searchInventoryByConditions(
            @RequestParam(required = false) String materialCode,
            @RequestParam(required = false) String materialName,
            @RequestParam(required = false) String warehouseCode,
            @RequestParam(required = false) String locationCode,
            @RequestParam(required = false) String batchNumber,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageResult<Inventory> pageResult = inventoryService.searchInventoryByConditions(
                materialCode, materialName, warehouseCode, locationCode, batchNumber, pageNum, pageSize);
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }
}
