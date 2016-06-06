package ec.com.tnb.mibus.ui.main;

import java.util.List;

import ec.com.tnb.mibus.data.model.Ribot;
import ec.com.tnb.mibus.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
