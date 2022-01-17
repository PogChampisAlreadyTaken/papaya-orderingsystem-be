package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Ordering;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Meal extends PanacheEntity {

    public String mealName;
    public int categoryid;
    public String hotness;
    public int menuid;
    public float price;
    public String ingredients;

    public static Meal findByMenuId(int menuid){
        return find("menuid", menuid).firstResult();
    }


    public static List<Meal> findByMenuCategory(int categoryid){
        return list("categoryid", categoryid);
    }
}
