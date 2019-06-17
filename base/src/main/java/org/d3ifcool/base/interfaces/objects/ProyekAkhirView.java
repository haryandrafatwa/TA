package org.d3ifcool.base.interfaces.objects;

import org.d3ifcool.base.models.ProyekAkhir;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/03/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public interface ProyekAkhirView {

    void showProgress();

    void hideProgress();

    void onGetObjectProyekAkhir(ProyekAkhir proyekAkhir);

    void isEmptyObjectProyekAkhir();

    void onFailed(String message);

}
