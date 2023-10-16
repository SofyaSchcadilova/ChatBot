package ru.anekdots.databasecontroller;

import java.util.Objects;

/**
 * Общая модель для баз данных
 */
public class BaseModel {
    /**
     * Уникальный индификатор записей
     */
    protected int id;

    public long getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public BaseModel(){};

    public BaseModel(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        BaseModel baseModel = (BaseModel) obj;
        return id == baseModel.id;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
