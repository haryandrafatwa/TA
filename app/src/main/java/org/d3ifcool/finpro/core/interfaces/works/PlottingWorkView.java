package org.d3ifcool.finpro.core.interfaces.works;

import org.d3ifcool.finpro.core.models.Plotting;

import java.util.List;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * Finpro
 * Copyright (C) 3/1/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public interface PlottingWorkView {

        void showProgress();

        void hideProgress();

        void onSucces();

        void onFailed(String message);

}
