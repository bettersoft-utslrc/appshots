package com.example.gruposhots.Dashboard;

public class PerfilPresenter implements PerfilActivity.Presenter, PerfilActivity.TaskListener {
    private PerfilActivity.View view;
    private PerfilActivity.Model model;

    public PerfilPresenter(PerfilActivity.View view) {
        this.view = view;
        model = new PerfilModel (this);
    }

    @Override
    public void onSave(String Nombre) {
        if(view !=null){
            view.disableInputs ();
            model.setNombre (Nombre);
        }

    }

    @Override
    public void onCharge() {
        if(view !=null){
            view.disableInputs ();
            model.chargeNombre ();
        }

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSucessSave() {
        if(view !=null){

            model.chargeNombre ();
        }
    }

    @Override
    public void onSucessCharge(String Nombre) {
        if (view !=null){
            view.enableInpuds ();
            view.fillEditText (Nombre);
        }
    }

    @Override
    public void onError(String error) {
        if(view !=null){
            view.enableInpuds ();
            view.onError (error);
        }
    }


}
