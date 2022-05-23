/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriacomputacion;

/**
 *
 * @author USUARIO
 */
public class MainTablas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//// //////////////////////////////////Ejemplo 1/////////////////////////////////////////////////////   
//        String simbolos = "1,2,3,4,5";
//        String estados = "A,B,C,D";                               
//        String transcripcion = "BC,DC,B,C,D,CDB,C,A,B,C,CD,D,D,B,B,C,A,B,C,D";
//        String finalizador ="";
// ///////////////////////////////Ejemplo 2///////////////////////////////////////////////////////       
//        String simbolos = "0,1,2";
//        String estados = "X,Y,Z,W,V";                  
//        String transcripcion = "Y,YZ,W,V,X,Z,WV,Y,X,VW,Z,Z,Z,Y,W";
//        String finalizador ="";  
//// ///////////////////////////////////////////////////////////////////////////////////////
//        String simbolos = "a,b,e,i";
//        String estados = "X,Y,Z,W,P,R,S";                  
//        String transcripcion = "Y,ZW,WS,P,Z,R,S,P,WRS,WS,Y,X,Y,R,R,P,SR,RP,WZ,ZY,R,S,P,YZ,YZP,PR,P,P";
//        String finalizador ="W";  
//// ///////////////////////////////////////////////////////////////////////////////////////
//        
//        String simbolos = "0,1";
//        String estados = "A,B,C,D,E,F,G";                  
//        String transcripcion ="E,B,B,CF,D,EG,G,D,F,G,E,G,C,D";
//        String finalizador ="";  
//// ///////////////////////////////////////////////////////////////////////////////////////
//        String simbolos = "0,1,2";
//        String estados = "B,A,C,D,E";                  
//        String transcripcion ="A,CD,E,C,D,E,C,DE,B,D,E,A,B,E,B";
//        String finalizador ="";  
//// ///////////////////////////////////////////////////////////////////////////////////////
//        String simbolos = "a,e";
//        String estados = "X,Y,Z,W,Q";                  
//        String transcripcion ="Y,W,WZ,YZ,QW,Q,W,X,Y,Z";
//        String finalizador = "W,Q";
//// ///////////////////////////////////////////////////////////////////////////////////////
//        String simbolos = "0,1,2";
//        String estados = "A,B,C,D,E,F,G,H,I";                   //////EJERCICIO QUE DEJO PARA ESTUDIAR
//        String transcripcion ="B,C,D,D,E,G,B,C,F,E,H,F,A,C,I,D,E,G,H,G,G,E,H,F,D,E,G";
//        String finalizador = "E";
//// ///////////////////////////////////////////////////////////////////////////////////////
//        String simbolos = "0,1";
//        String estados = "A,B,C,D,E,F";                   /////ejercicio clase https://drive.google.com/drive/shared-with-me
//        String transcripcion ="B,C,D,E,F,B,E,C,D,E,F,B";
//        String finalizador = "D";

//// ///////////////////////////////////////////////////////////////////////////////////////
//        String simbolos = "0,1,2,3";
//        String estados = "A,B,C,D,E";                   //////EJERCICIO QUE DEJO PARA ESTUDIAR
//        String transcripcion ="B,CE,D,C,B,B,A,EC,D,E,BD,B,A,B,C,D,D,E,EB,C";
//        String finalizador = "A";
        
        
//// ///////////////////////////////////////////////////////////////////////////////////////
//        String simbolos = "a,e";
//        String estados = "X,Y,Z,W,P,R";                   //////EJERCICIO QUE DEJO PARA ESTUDIAR
//        String transcripcion ="Y,W,ZP,ZP,RW,R,P,P,R,W,X,Z";
//        String finalizador = "W";
        
        
//// ///////////////////////////////////////////////////////////////////////////////////////
//        String simbolos = "1,2,3";
//        String estados = "P,Q,R,S,T,U,V";                   //////EJERCICIO QUE DEJO PARA ESTUDIAR
//        String transcripcion ="Q,P,T,R,S,T,S,U,V,T,Q,V,R,S,T,V,S,S,S,U,V";
//        String finalizador = "T";
       
//        String simbolos = "0,1,2";
//        String estados = "A,B,C,D,E,F";                   //////PREVIO PUNTO 1
//        String transcripcion ="CD,E,A,F,E,D,B,C,F,B,DE,F,F,F,E,A,B,C";
//        String finalizador = "F"; 
// //////////////////////////////
                  String simbolos = "0,1";
        String estados = "A,B,C,D,E,F,G";                  
        String transcripcion ="E,B,B,CF,D,EG,G,D,F,G,E,G,C,D";
        String finalizador ="";

        String []arreglo = transcripcion.split(",");
        String transcripcionPC="";
     
        for (int i = 0; i < arreglo.length; i++) {
            transcripcionPC+=arreglo[i];
            
            if((i+1)%simbolos.split(",").length==0){
                transcripcionPC+=";";
            }else{
            transcripcionPC+=",";
            }
            
        }
        
        Tablas p = new Tablas(simbolos, estados, transcripcion,transcripcionPC,finalizador);
        p.cargarTabla();
    }

}
