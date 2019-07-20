package com.example.gruposhots.Dashboard;

import android.view.View;

public interface PerfilActivity {
    interface View{
        void enableInpuds();
        void disableInputs();

        void fillEditText(String Nombre,String Email,String Password);
        void onError(String error);
    }

    interface Presenter{
        void onSave(String Nombre,String Email,String Password);
        void onCharge();

        void onDestroy();
    }
    interface Model{
        void chargeNombre();
        void setNombre(String Nombre);
        void chargeEmail();
        void setEmail(String Email);
        void chargePassword();
        void setPassword(String Password);

    }

    interface TaskListener{
        void onSucessSave();
        void onSucessCharge(String Nombre, String Email, String Password);

        void onError(String error);

    }
}
