/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2021    HORA: 10-11 HRS
:*
:*                       Clase para enviar emails
:*
:*  Archivo     : SendMail.java
:*  Autor       : Angel Eduardo Soto García     17130848
:*                José Antonio Zandate Luna     17130854
:*                Patricia García Almanza       17130028
:*                Ricardo Juarez Martìnez       17130043
:*  Fecha       : 28/Junio/2021
:*  Compilador  : Android Studio 4.1.2
:*  Descripción : Clase para poder enviar emails directamente desde el activity
:*                ListaTotalAlumnosActivity recibiendo el email, asunto y mensaje
:*  Ultima modif:
:*
:*==========================================================================================
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.equipo3.asistenciasapp.SendEmail;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail extends AsyncTask{
    private Context context;
    private Session session;
    private String email;
    private String subject;
    private String message;
    private ProgressDialog progressDialog;

    //----------------------------------------------------------------------------------------------

    public SendMail( Context context, String email, String subject, String message ){
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    //----------------------------------------------------------------------------------------------

    @Override
    protected Object doInBackground( Object[] objects ) {
        Properties props = new Properties();
        props.put( "mail.smtp.host", "smtp.gmail.com" );
        props.put( "mail.smtp.socketFactory.port", "465" );
        props.put( "mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory" );
        props.put( "mail.smtp.auth", "true" );
        props.put( "mail.smtp.port", "465" );
        session = Session.getDefaultInstance( props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication( Config.EMAIL, Config.PASSWORD );
            }
        });
        try {
            MimeMessage mm = new MimeMessage( session );
            mm.setFrom( new InternetAddress( Config.EMAIL) );
            mm.addRecipient( Message.RecipientType.TO, new InternetAddress( email ) );
            mm.setSubject( subject );
            mm.setText( message );
            Transport.send( mm );
        }
        catch ( MessagingException e ) {
            e.printStackTrace();
        }
        return null;
    }

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show( context,"Enviando email","Por favor espere...",false,false );
    }

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onPostExecute( Object o ) {
        super.onPostExecute( o );
        progressDialog.dismiss();
        Toast.makeText( context,"Email enviado",Toast.LENGTH_LONG ).show();
    }

    //----------------------------------------------------------------------------------------------

}

class Config {
    public static final String EMAIL ="app.asistencias.equipo3.andr@gmail.com";
    public static final String PASSWORD ="4MHk0fKIZwhOaAr";
}
