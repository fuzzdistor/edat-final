package Estructuras;

public class Nodo {
    private Object elem;
    private Nodo enlace;

    //constructor
    public Nodo(Object elemnto){
        this.elem=elemnto;
        this.enlace=null;
    }
    public Nodo(Object elem, Nodo enlace){
        this.elem=elem;
        this.enlace= enlace;
    }
    //modificadoras
    public void setElem(Object elem){
        this.elem=elem;
    }
    public void setEnlace(Nodo enlace){
        this.enlace=enlace;
    }
    //observadoras
    public Object getElem(){
        return elem;
    }
    public Nodo getEnlace(){
        return enlace;
    }
    public boolean equals(Nodo otroNodo){
        return this.elem==otroNodo.getElem()&& this.enlace==otroNodo.getEnlace();

    }
    
}
