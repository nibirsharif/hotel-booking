package com.contoso.service.command;

public interface BaseModel<T, E> {

	public T create(E e) throws Throwable;
}
