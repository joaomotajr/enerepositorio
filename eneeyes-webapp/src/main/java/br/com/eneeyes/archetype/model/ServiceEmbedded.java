package br.com.eneeyes.archetype.model;


public abstract class ServiceEmbedded<T> {
	public abstract T toNativeBean(T source);
}
