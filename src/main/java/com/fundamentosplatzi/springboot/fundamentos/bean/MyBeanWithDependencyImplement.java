package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement  implements MyBeanWithDependency{


    private final Log LOGGER= LogFactory.getLog(MyBeanWithDependencyImplement.class);
    private  MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation){
        this.myOperation=myOperation;

    }


    @Override
    public void printWithDependency() {
        LOGGER.info("Hemos ingresado al metodo printWithDependency");
        System.out.println(myOperation.suma(3));
        LOGGER.debug("El numero enviado como parametro a la dependencia operation es:"+myOperation.suma(3));

        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
