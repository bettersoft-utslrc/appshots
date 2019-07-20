package com.example.gruposhots.Dashboard;

import android.view.View;

public interface PerfilActivity {
    interface View{
        void enableInpuds();
        void disableInputs();

        void fillEditText(String Nombre);
        void onError(String error);
    }

    interface Presenter{
        void onSave(String Nombre);
        void onCharge();

        void onDestroy();
    }
    interface Model{
        void chargeNombre();
        void setNombre(String Nombre);

    }

    interface TaskListener{
        void onSucessSave();
        void onSucessCharge(String Nombre);
        void onError(String error);

    }
}
