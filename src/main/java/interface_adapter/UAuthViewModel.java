package interface_adapter;

import usecase.UserAuth.UAuthOutputBoundary;

import java.beans.PropertyChangeListener;

public class UAuthViewModel extends ViewModel {

    public UAuthViewModel()
    {
        super("UAuth");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
