package interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.net.URL;

public class UAuthViewModel extends ViewModel {

    private String url;

    public UAuthViewModel() {
        super("UAuth");
    }

    public void setUrl(String url) {
        this.url = url;
        firePropertyChanged(); // Notify observers about the change
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void firePropertyChanged() {
        // Implement logic to notify observers
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // Implement logic to add property change listeners
    }
}
