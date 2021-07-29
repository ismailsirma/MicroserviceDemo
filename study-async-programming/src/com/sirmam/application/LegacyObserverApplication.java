package com.sirmam.application;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import com.sirmam.event.TradeEvent;

public class LegacyObserverApplication {
	
	public static void main(String[] args) {
		Observable observable = new TradeEventObservable();
		Observer slowObserver = (o, tradeEvent) -> {
			try { TimeUnit.SECONDS.sleep(3); } catch(InterruptedException e) { e.printStackTrace();}
			System.err.println("Slow observer: " + tradeEvent);
		};

		Observer fastObserver = (o, tradeEvent) -> {
			System.err.println("fast observer:" + tradeEvent);
		};		
		
		observable.addObserver(slowObserver);
		observable.addObserver(fastObserver);
		
		var tradeEvents = List.of(
				new TradeEvent("ORCL", 100.0, 50),
				new TradeEvent("IBM", 70.0, 250),
				new TradeEvent("MSFT", 110.0, 500),
				new TradeEvent("ORCL", 102.0, 250),
				new TradeEvent("MSFT", 112.0, 2500)
				);
		
		tradeEvents.forEach(observable::notifyObservers);
	}
}

class TradeEventObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}
	
}
