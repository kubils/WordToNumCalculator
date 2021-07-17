package com.ks.converter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


import java.util.Hashtable;
import java.util.Locale;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ServiceRegistration registration;

	public void start(BundleContext bundleContext) throws Exception {

		Locale trlocale= new Locale("tr_TR");
		Locale.setDefault(trlocale);

		// set jvm locale to TR
		//Locale.setDefault(trlocale);
		
		// set jvm locale to ENG
		//Locale.setDefault(Locale.US);

		System.out.println(Locale.getDefault());
		
		Activator.context = bundleContext;
		
		Converter converter = new ConverterImp();
		
		registration = context.registerService(Converter.class.getName(), 
				converter, new Hashtable<String, Object>());
		
		System.out.println("Service registered : Converter");
		System.out.println(registration.toString());

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		registration.unregister();

	}

}
