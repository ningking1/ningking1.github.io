package com.wms.mapper;

import com.wms.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface InventoryMapper {
    // 查询所有库存记录
    List<Inventory> findAll();
    
    // 根据ID查询库存记录
    Inventory findById(Long id);
    
    // 根据物料编码查询库存记录
    List<Inventory> findByMaterialCode(String materialCode);
    
    // 分页查询所有库存记录
    List<Inventory> findAllByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);
    
    // 获取所有库存记录总数
    long findTotalCount();
    
    // 分页查询指定物料编码的库存记录
    List<Inventory> findByMaterialCodeByPage(@Param("materialCode") String materialCode, 
                                            @Param("offset") int offset, 
                                            @Param("pageSize") int pageSize);
    
    // 获取指定物料编码的库存记录总数
    long findTotalCountByMaterialCode(String materialCode);
    
    // 多条件分页查询库存记录
    List<Inventory> findByConditionsPage(
            @Param("materialCode") String materialCode,
            @Param("materialName") String materialName,
            @Param("warehouseCode") String warehouseCode,
            @Param("locationCode") String locationCode,
            @Param("batchNumber") String batchNumber,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize);
    
    // 获取多条件查询的库存记录总数
    long findTotalCountByConditions(
            @Param("materialCode") String materialCode,
            @Param("materialName") String materialName,
            @Param("warehouseCode") String warehouseCode,
            @Param("locationCode") String locationCode,
            @Param("batchNumber") String batchNumber);
}
