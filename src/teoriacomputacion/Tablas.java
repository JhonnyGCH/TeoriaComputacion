/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriacomputacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class Tablas {

    private String simbolos, estados, completos;
    private String[][] tabla;
    private String[][] tablaAFD;
    private List<String> estadosActuales = new ArrayList<>();
    private String[] estadosFinal;
    private String[] simbol;
    private Byte f, c;
    private int fa = 1;
    private Byte contadorDese = 0;
    private int nuevosEstados = 0;
    private String transcripcionSep;

    public Tablas() {
    }

    public Tablas(String simbolos, String estados, String completos, String separada, String finalizador) {
        this.simbolos = simbolos;
        this.estados = estados;
        this.completos = completos;
        this.transcripcionSep = separada;
        this.tabla = new String[estados.split(",").length + 1][simbolos.split(",").length + 1];
        this.simbol = this.simbolos.split(",");
        this.estadosFinal = finalizador.split(",");
    }

    private void conversion() {
        System.out.println("TABLA ORIGINAL:\n" + this.mostrarTabla() + "\n=======================");
        String[] estadosNue = estadosNuevos().split(",");
        Byte nuevaCEstados = (byte) (estadosNue.length + 1);
        copiarVectorDimensionado(nuevaCEstados);

    }
    
    public Boolean esFinalizador(String estado) {
        if (estado.isEmpty()) {
            return false;
        }
        for (Byte i = 0; i < estado.length(); i++) {

            String letrai = String.valueOf(estado.charAt(i));

            for (Byte j = 0; j < this.estadosFinal.length; j++) {

                if (letrai.equals(estadosFinal[j])) {
                    return true;
                }

            }
        }
        return false;

    }

    public void cargarTabla() {
        llenarSimbolosEstados();
        llenarCompletos();
        conversion();
    }

    private void llenarCompletos() {
        String[] comp = completos.split(",");
        Byte index = -1;
        for (Byte i = 1; i < f; i++) {
            for (Byte j = 1; j < c; j++) {
                if (index + 1 < comp.length) {
                    index++;
                }
                tabla[i][j] = this.ordenarEstado(comp[index]);
            }
        }
    }

    private String estadosNuevos() {

        String mensaje = "";

        String[] comp = this.transcripcionSep.split(";");
        this.estadosActuales.add(tabla[1][0]);
        String[] estados = comp[0].split(",");
        for (Byte j = 0; j < estados.length; j++) {
            if (!this.existeEstadoAc(estados[j])) {
                mensaje += estados[j] + ",";
            }
        }
        return mensaje;
    }

    public Boolean existeEstadoAc(String estado) {
        for (Byte j = 0; j < this.estadosActuales.size(); j++) {

            if (this.estadosIguales(estado, estadosActuales.get(j))) {
                return true;
            }
        }
        estado = this.ordenarEstado(estado);
        this.estadosActuales.add(estado);
        return false;
    }

    public String ordenarEstado(String estado) {
        List<String> lista = new LinkedList<>();
        String estadoOrdenado = "";
        for (Byte i = 0; i < estado.length(); i++) {
            String a = String.valueOf(estado.charAt(i));
            lista.add(a);
        }
        Collections.sort(lista);

        for (Object a : lista) {
            estadoOrdenado += a + "";
        }

        return estadoOrdenado;
    }

    private void llenarSimbolosEstados() {
        String[] simbSeparados = simbolos.split(",");
        String[] estadosSeparados = estados.split(",");

        this.f = (byte) (estadosSeparados.length + 1);
        this.c = (byte) (simbSeparados.length + 1);
        tabla[0][0] = "Q";
        for (Byte i = 1; i <= estadosSeparados.length; i++) {
            tabla[i][0] = (estadosSeparados[i - 1]);
        }

        for (Byte j = 1; j <= simbSeparados.length; j++) {
            tabla[0][j] = (simbSeparados[j - 1]);
        }
    }

    public String valorEnIndice(String estado, String simbolo) {
        Byte i, j;
        for (i = 1; i < c; i++) {//simbolos
            if (simbolo.equals(tabla[0][i])) {
                break;
            }
        }
        for (j = 1; j < f; j++) {//estados
            if (estado.equals(tabla[j][0])) {
                break;
            }
        }
        return tabla[j][i];
    }
    
    public String valorEnIndiceAFD(String estado, String simbolo) {
        Byte i, j;
        for (i = 1; i < c; i++) {//simbolos
            if (simbolo.equals(tablaAFD[0][i])) {
                break;
            }
        }
        for (j = 1; j < fa-1; j++) {//estados
            if (estado.equals(tablaAFD[j][0])) {
                break;
            }
        }
        return tablaAFD[j][i];
    }

    public String mostrarTablaAFD() {

        String mensaje = "";
        String[][] nuevaTablaAFD = new String[this.estadosActuales.size() + 1][this.simbol.length + 1];
        Boolean vacio = false;
        for (Byte i = 0; i < fa && vacio == false; i++) {
            for (Byte j = 0; j < c && vacio == false; j++) {
                if (tablaAFD[i][j] != null) {
                    nuevaTablaAFD[i][j] = tablaAFD[i][j];
                    mensaje += (tablaAFD[i][j] + "	");
                } else {
                    vacio = true;
                }
            }
            if (vacio == false) {
                mensaje += "\n";
            }
        }
        this.fa=this.estadosActuales.size() + 1;
        this.tablaAFD = nuevaTablaAFD;

        return mensaje;
    }

    public String mostrarAFDN() {
        String mensaje = "";
        for (Byte i = 0; i < estadosActuales.size() + 1; i++) {
            for (Byte j = 0; j < simbol.length + 1; j++) {
                mensaje += (tablaAFD[i][j] + "	");
            }
            mensaje += "\n";
        }
        return mensaje;
    }

    public boolean estadosIguales(String est1, String est2) {

        if (est1.length() != est2.length()) {
            return false;
        }
        Boolean iguales = false;
        for (Byte i = 0; i < est1.length(); i++) {

            char estadoSolo1 = est1.charAt(i);

            for (Byte j = 0; j < est2.length(); j++) {
                char estadoSolo2 = est2.charAt(j);

                if (estadoSolo2 == estadoSolo1) {
                    iguales = true;
                }
            }
            if (iguales == false) {
                return false;
            }

            iguales = false;
        }
        return true;
    }

    private void copiarVectorDimensionado(Byte nuevaCEstados) {
        this.fa = +(nuevaCEstados + fa);
        this.tablaAFD = new String[fa][simbolos.split(",").length + 1];
        tablaAFD[0][0] = "Q";
        for (Byte i = 1; i < simbolos.split(",").length + 1; i++) {
            tablaAFD[0][i] = tabla[0][i];
        }

        for (Byte i = 1; i < fa; i++) {
            if ((i - 1) < estadosActuales.size()) {
                tablaAFD[i][0] = this.estadosActuales.get(i - 1);
            }
        }
        llenarCompletosAFD();
    }

    public void llenarCompletosAFD() {
        if (nuevosEstados != 0) {
            nuevosEstados = 0;
        }
        this.contadorDese++;
        String mensaje = "";
        for (Byte i = 1; i < this.estadosActuales.size() + 1; i++) {
            for (Byte j = 1; j < this.c; j++) {
                if (i < fa && tablaAFD[i][0] != null) {
                    if (this.existeEstado(tablaAFD[i][0])) {

                        tablaAFD[i][j] = this.ordenarEstado(this.valorEnIndice(tablaAFD[i][0], simbol[j - 1] + ""));
                        existeEstadoAc(tablaAFD[i][j]);
                    } else {
                        nuevosEstados += (llenarEstadoDesconocido(tablaAFD[i][0], i, j));
                        if (j == 1) {
                            mensaje += "\n" + (this.procesoDeSeparacion(tablaAFD[i][0])[0]);
                        }
                    }
                }
            }
        }

        if (contadorDese == 70) {
            System.out.println("TABLA AFD:\n" + this.mostrarTablaAFD() + "\nPROCESO:\n");
            System.out.println(mensaje);
            return;
        }
        this.copiarVectorDimensionado((byte) (nuevosEstados + 1));
    }

    public String mostrarTabla() {
        String mensaje = "";
        for (Byte i = 0; i < f; i++) {
            for (Byte j = 0; j < c; j++) {
                if (tabla[i][j] != null) {
                    mensaje += (tabla[i][j] + "	");
                }
            }
            mensaje += "\n";
        }
        return mensaje;
    }


    public String[] procesoDeSeparacion(String estado) {
        String mensaje = "";
        String[] total = new String[2];
        total[1] = "";
        Byte aux = 0;
        String msg = "";
        String unionEstados = "";
        for (Byte i = 0; i < estado.length() && aux < simbol.length;) {
            char estadoI = estado.charAt(i);
            String valorEnIndice = this.valorEnIndice(String.valueOf(estadoI), simbol[aux]);
            mensaje += estadoI + "," + simbol[aux] + "-->	" + valorEnIndice + "\n";
            unionEstados += valorEnIndice + ",";
            i++;
            if (i == estado.length()) {
                i = 0;
                String estUnidos = unirEstados(unionEstados);
                total[1] += estUnidos + ";";
                msg += ("[" + estado + "]" + "," + simbol[aux] + "-->	" + this.ordenarEstado(estUnidos) + "\n" + mensaje + "\n");
                mensaje = "";
                aux++;
                unionEstados = "";
            }
        }
        total[0] = msg+"\n================================\n";
        return total;
    }

    private boolean existeEstado(String estado) {
        for (Byte j = 1; j < this.f; j++) {//estados
            if (this.estadosIguales(estado, tabla[j][0])) {
                return true;
            }
        }
        return false;
    }

    public String unirEstados(String unionEstados) {
        String[] esta = unionEstados.split(",");
        String primerEstado = esta[0];
        String estadosUnidos = primerEstado;
        for (Byte i = 1; i < esta.length; i++) {
            String estadoI = esta[i];
            for (Byte j = 0; j < estadoI.length(); j++) {
                String primeraLetra = String.valueOf(estadoI.charAt(j));
                if (!estaEn(primeraLetra, estadosUnidos)) {
                    estadosUnidos += primeraLetra;
                }
            }
        }
        return estadosUnidos;
    }

    public boolean estaEn(String primeraLetra, String estadoI) {
        for (Byte i = 0; i < estadoI.length(); i++) {
            if (primeraLetra.equals(String.valueOf(estadoI.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    private Byte llenarEstadoDesconocido(String estado, Byte i, Byte j) {
        Byte cantidadNEstados = 0;
        String[] total = this.procesoDeSeparacion(estado);
        String[] esta = total[1].split(";");
        tablaAFD[i][j] = ordenarEstado(esta[j - 1]);
        if (!this.existeEstadoAc(esta[j - 1])) {
            cantidadNEstados++;
        }
        return cantidadNEstados;
    }
}