/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pr.corina.lab2pr.entities;

import com.pr.corina.lab2pr.utils.DoubleConverter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

/**
 *
 * @author corina
 */
@CsvDataType()
public class Category implements Serializable {
    
    private static final long serialVersionUID = 371968159365574089L;
 
    @CsvField(pos = 1, required = true)
    private int id;
    
    @CsvField(pos = 2, required = true)
    private String name;
    
    @CsvField(pos = 3)
    private int categoryId;
    
    private boolean checked;
    
    private Double total=0d;
    
    private List<Category>categoryList=new ArrayList();

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    public void addToTotal(Double toAdd){
        this.total+=toAdd;
        this.total=DoubleConverter.round(this.total, 2);
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    public String customToString(Category category){
         StringBuilder sb=new StringBuilder();
         sb.append("Category{").append("id=").append(id).append(", name=").append(name)
           .append(", categoryId=").append(categoryId);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("Category{").append("id=").append(id).append(", name=").append(name)
          .append(", categoryId=").append(categoryId)
          .append(", total=").append(total);
       
        sb.append(" Subcategories{").append(categoryList.toString()).append("}");
        
        sb.append("}");
        return sb.toString();
    }
    
    
}
