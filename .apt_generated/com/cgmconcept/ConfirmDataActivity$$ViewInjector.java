// Generated code from Butter Knife. Do not modify!
package com.cgmconcept;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ConfirmDataActivity$$ViewInjector {
  public static void inject(Finder finder, final com.cgmconcept.ConfirmDataActivity target, Object source) {
    View view;
    view = finder.findById(source, 2130968598);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968598' for field 'targetSpeed' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.targetSpeed = (android.widget.TextView) view;
    view = finder.findById(source, 2130968602);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968602' for field 'inletTS' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.inletTS = (android.widget.TextView) view;
    view = finder.findById(source, 2130968597);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968597' for field 'carbonContent' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.carbonContent = (android.widget.TextView) view;
    view = finder.findById(source, 2130968595);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968595' for field 'outlet' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.outlet = (android.widget.TextView) view;
    view = finder.findById(source, 2130968596);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968596' for field 'nOfDies' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.nOfDies = (android.widget.TextView) view;
    view = finder.findById(source, 2130968600);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968600' for field 'averagereduction' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.averagereduction = (android.widget.TextView) view;
    view = finder.findById(source, 2130968604);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968604' for field 'btnCalculate' and method 'submit' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.btnCalculate = (android.widget.Button) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.submit();
        }
      });
    view = finder.findById(source, 2130968599);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968599' for field 'speedwireinlet' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.speedwireinlet = (android.widget.TextView) view;
    view = finder.findById(source, 2130968603);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968603' for field 'outletTS' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.outletTS = (android.widget.TextView) view;
    view = finder.findById(source, 2130968594);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968594' for field 'inlet' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.inlet = (android.widget.TextView) view;
    view = finder.findById(source, 2130968601);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968601' for field 'totalReduction' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.totalReduction = (android.widget.TextView) view;
  }

  public static void reset(com.cgmconcept.ConfirmDataActivity target) {
    target.targetSpeed = null;
    target.inletTS = null;
    target.carbonContent = null;
    target.outlet = null;
    target.nOfDies = null;
    target.averagereduction = null;
    target.btnCalculate = null;
    target.speedwireinlet = null;
    target.outletTS = null;
    target.inlet = null;
    target.totalReduction = null;
  }
}
