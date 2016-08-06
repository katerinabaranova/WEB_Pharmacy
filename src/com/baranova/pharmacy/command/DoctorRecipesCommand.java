package com.baranova.pharmacy.command;

import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.service.ServiceRecipe;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class command for filling the list of doctor's recipes.
 */
public class DoctorRecipesCommand implements ICommand{

    @Override
    public PageName execute(HttpServletRequest request){
        List<Recipe> recipes=ServiceRecipe.findDoctorRecipe(request);
    }
}
