package mx.edu.itl.c18131260.androlib.util.permisos;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ChecadorDePermisos {

    public static final int CODIGO_PEDIR_PERMISOS = 44;

    public static void checarPermisos (Activity activity, PermisoApp [] permisosRequeridos) {
        //si el arreglo de ermisos está vacío, regresamos

        if(permisosRequeridos == null || permisosRequeridos.length == 0){
            return;
        }
        //verifica cuales permisos están otorgados, y marcarlos
        for(int i = 0; i < permisosRequeridos.length; i++){
            if(ContextCompat.checkSelfPermission(activity, permisosRequeridos[i].getPermiso())
            == PackageManager.PERMISSION_GRANTED){
                permisosRequeridos[i].setOtorgado(true);
            }
        }
        //determinar la lista de permisos que no están otorgados
        ArrayList<String> temp = new ArrayList<>();
        //agregar permisos que no están activados
        for(int i=0; i<permisosRequeridos.length; i++){
            if(permisosRequeridos[i].isOtorgado() == false){
                //permiso no otorgado, agregar a arreglo
                temp.add(permisosRequeridos[i].getPermiso());
            }
        }
        //si hay permisos sin otorgar, pedírselos al usuario
        if(temp.size() > 0){
            //convertir arraylist temporal a un arreglo de strings
            String [] permisosPendientes = new String[temp.size()];
            temp.toArray(permisosPendientes);

            ActivityCompat.requestPermissions(activity, permisosPendientes, CODIGO_PEDIR_PERMISOS);
        }
    }


    public static void verificarPermisosSolicitados (Activity activity, PermisoApp[] permisosReq,
                                                     String [] permissions, int[] grantResults) {
        //si el usuario no da un permiso que es obligatorio
        ArrayList<String> temp = new ArrayList<>();
        for (int i=0; i<permisosReq.length; i++){
            temp.add(permisosReq[i].getPermiso());
        }
        String permisosObligatoriosNoOtorgados = "";
        if(grantResults.length > 0){
            //recorrer el arreglo de resultados de permisos otorgados
            for(int i=0; i<grantResults.length; i++){
                //buscar el nombre del permiso pendiente en los permisos de la app
                int index = temp.indexOf(permissions[i]);
                //si esl usuario otogró el permiso pendiente
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    permisosReq[index].setOtorgado(true);
                } else if(permisosReq[index].isObligatorio()){
                    //si no se otorgó un permiso que es obligatorio, pasar al string de no obligatorios
                    permisosObligatoriosNoOtorgados += permisosReq[index].getNombreCorto()+", ";
                }
            }
            //checar si hay permisos obligatorios que no otorgó el usuario
            if(!permisosObligatoriosNoOtorgados.isEmpty()){
                alertarYsalir(activity, permisosObligatoriosNoOtorgados);
            }
        }
    }

    private static void alertarYsalir(Activity activity, String permisosObligatoriosNoOtorgados) {
        //avisar al usuario que no se puede continuar y salimos del activity
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Permisos requeridos")
                .setMessage("Los siguientes permisos son necesarios para" +
                        "usar esta funcionalidad: \n\n" +
                        permisosObligatoriosNoOtorgados+"\n\n"+
                        "Conceda los permisos cuando se solicitan.")
                .setCancelable(false)
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
                    }
                }).create().show();
    }


}
