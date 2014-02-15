// Generated code from Butter Knife. Do not modify!
package com.cgmconcept;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ConfirmData$$ViewInjector {
  public static void inject(Finder finder, final com.cgmconcept.ConfirmData target, Object source) {
    View view;
    view = finder.findById(source, 2130968606);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968606' for field 'inletTS' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.inletTS = (android.widget.TextView) view;
    view = finder.findById(source, 2130968600);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968600' for field 'inlet' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.inlet = (android.widget.TextView) view;
    view = finder.findById(source, 2130968596);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968596' for field 'nOfDies' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.nOfDies = (android.widget.TextView) view;
    view = finder.findById(source, 2130968605);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968605' for field 'totalReduction' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.totalReduction = (android.widget.TextView) view;
    view = finder.findById(source, 2130968601);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968601' for field 'outlet' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.outlet = (android.widget.TextView) view;
    view = finder.findById(source, 2130968602);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968602' for field 'targetSpeed' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.targetSpeed = (android.widget.TextView) view;
    view = finder.findById(source, 2130968604);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968604' for field 'averagereduction' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.averagereduction = (android.widget.TextView) view;
    view = finder.findById(source, 2130968607);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968607' for field 'outletTS' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.outletTS = (android.widget.TextView) view;
    view = finder.findById(source, 2130968597);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968597' for field 'carbonContent' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.carbonContent = (android.widget.TextView) view;
    view = finder.findById(source, 2130968603);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968603' for field 'speedwireinlet' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.speedwireinlet = (android.widget.TextView) view;
    view = finder.findById(source, 2130968599);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2130968599' for field 'confirmButton' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.confirmButton = (android.widget.Button) view;
  }

  public static void reset(com.cgmconcept.ConfirmData target) {
    target.inletTS = null;
    target.inlet = null;
    target.nOfDies = null;
    target.totalReduction = null;
    target.outlet = null;
    target.targetSpeed = null;
    target.averagereduction = null;
    target.outletTS = null;
    target.carbonContent = null;
    target.speedwireinlet = null;
    target.confirmButton = null;
  }
}
