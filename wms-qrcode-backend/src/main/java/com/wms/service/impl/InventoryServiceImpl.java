package com.wms.service.impl;

import com.wms.entity.Inventory;
import com.wms.entity.PageResult;
import com.wms.mapper.InventoryMapper;
import com.wms.service.InventoryService;
import com.wms.util.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;
    
    @Value("${qrcode.width}")
    private int qrCodeWidth;
    
    @Value("${qrcode.height}")
    private int qrCodeHeight;

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryMapper.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return inventoryMapper.findById(id);
    }

    @Override
    public List<Inventory> getInventoriesByMaterialCode(String materialCode) {
        return inventoryMapper.findByMaterialCode(materialCode);
    }

    @Override
    public String generateInventoryQrCode(Long inventoryId) {
        Inventory inventory = getInventoryById(inventoryId);
        if (inventory == null) {
            throw new RuntimeException("Inventory not found with id: " + inventoryId);
        }
        
        String qrContent = String.format(
            "物料信息\n编码:%s\n名称:%s\n数量:%d\n批次:%s\n仓库:%s\n库位:%s",
            inventory.getMaterialCode(),
            inventory.getMaterialName(),
            inventory.getQuantity(),
            inventory.getBatchNumber(),
            inventory.getWarehouseCode(),
            inventory.getLocationCode()
        );
        
        // 生成二维码并返回Base64编码
        try {
            return QRCodeUtil.generateQRCodeBase64(qrContent, qrCodeWidth, qrCodeHeight);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate QR code: " + e.getMessage());
        }
    }
    
    @Override
    public PageResult<Inventory> getInventoryByPage(int pageNum, int pageSize) {
        // 计算偏移量
        int offset = (pageNum - 1) * pageSize;
        // 获取分页数据
        List<Inventory> data = inventoryMapper.findAllByPage(offset, pageSize);
        // 获取总记录数
        long total = inventoryMapper.findTotalCount();
        // 创建并返回分页结果
        return new PageResult<>(data, total, pageNum, pageSize);
    }
    
    @Override
    public PageResult<Inventory> getInventoryByMaterialCodePage(String materialCode, int pageNum, int pageSize) {
        // 计算偏移量
        int offset = (pageNum - 1) * pageSize;
        // 获取分页数据
        List<Inventory> data = inventoryMapper.findByMaterialCodeByPage(materialCode, offset, pageSize);
        // 获取总记录数
        long total = inventoryMapper.findTotalCountByMaterialCode(materialCode);
        // 创建并返回分页结果
        return new PageResult<>(data, total, pageNum, pageSize);
    }
    
    @Override
    public PageResult<Inventory> searchInventoryByConditions(String materialCode, String materialName, 
                                                         String warehouseCode, String locationCode, 
                                                         String batchNumber, int pageNum, int pageSize) {
        // 计算偏移量
        int offset = (pageNum - 1) * pageSize;
        // 获取分页数据
        List<Inventory> data = inventoryMapper.findByConditionsPage(materialCode, materialName, 
                                                                 warehouseCode, locationCode, 
                                                                 batchNumber, offset, pageSize);
        // 获取总记录数
        long total = inventoryMapper.findTotalCountByConditions(materialCode, materialName, 
                                                             warehouseCode, locationCode, batchNumber);
        // 创建并返回分页结果
        return new PageResult<>(data, total, pageNum, pageSize);
    }
}
