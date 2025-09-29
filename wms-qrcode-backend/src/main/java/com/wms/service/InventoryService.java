package com.wms.service;

import com.wms.entity.Inventory;
import com.wms.entity.PageResult;
import java.util.List;

public interface InventoryService {
    // 获取所有库存记录
    List<Inventory> getAllInventories();
    
    // 根据ID获取库存记录
    Inventory getInventoryById(Long id);
    
    // 根据物料编码获取库存记录
    List<Inventory> getInventoriesByMaterialCode(String materialCode);
    
    // 生成库存二维码
    String generateInventoryQrCode(Long inventoryId);
    
    // 分页获取所有库存记录
    PageResult<Inventory> getInventoryByPage(int pageNum, int pageSize);
    
    // 根据物料编码分页获取库存记录
    PageResult<Inventory> getInventoryByMaterialCodePage(String materialCode, int pageNum, int pageSize);
    
    // 多条件分页获取库存记录
    PageResult<Inventory> searchInventoryByConditions(
            String materialCode,
            String materialName,
            String warehouseCode,
            String locationCode,
            String batchNumber,
            int pageNum,
            int pageSize);
}
