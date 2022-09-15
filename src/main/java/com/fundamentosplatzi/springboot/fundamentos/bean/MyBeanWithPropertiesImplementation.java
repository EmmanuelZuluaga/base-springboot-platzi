package com.fundamentosplatzi.springboot.fundamentos.bean;

import com.fundamentosplatzi.springboot.fundamentos.FundamentosApplication;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithPropertiesImplementation implements MyBeanWithProperties{



    private String nombre;
    private String apellido;

    public MyBeanWithPropertiesImplementation(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return nombre +"-"+apellido;
    }
}
