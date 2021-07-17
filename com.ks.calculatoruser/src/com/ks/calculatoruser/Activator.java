package com.ks.calculatoruser;

import java.util.Locale;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.ks.converter.Converter;

public class Activator implements BundleActivator {

	private static BundleContext context;
	

	static BundleContext getContext() {

		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {

		Activator.context = bundleContext;
		ServiceReference<?> reference = context.getServiceReference(Converter.class.getName());

		if (reference != null) {
			Converter service = (Converter) context.getService(reference);

			CalculatorSwing.main(service);

			context.ungetService(reference);
		} else {
			System.out.println("No service available");
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
