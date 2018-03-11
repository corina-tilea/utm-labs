/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pr.corina.lab2pr.entities;

import com.pr.corina.lab2pr.utils.DoubleConverter;
import java.io.Serializable;
import java.util.Date;
import net.sf.jsefa.common.converter.BigDecimalConverter;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

/**
 *
 * @author corina
 */
@CsvDataType()
public class Order implements Serializable{
    
    private static final long serialVersionUID = 371968159365574089L;
    
    @CsvField(pos = 1)
    private String id;
    
    @CsvField(pos = 2, converterType = DoubleConverter.class)
    private Double total;
    
    @CsvField(pos = 3, required = true)
    private int categoryId;
    
    @CsvField(pos = 4, format = "yyyy-MM-dd", required = true)
    private Date createdAt;

    
   

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", total=" + total + ", categoryId=" + categoryId + ", createdAt=" + createdAt + '}';
    }

   
    
}
