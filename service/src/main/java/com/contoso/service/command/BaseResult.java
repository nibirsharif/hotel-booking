package com.contoso.service.command;

public interface BaseResult<T, E> {

	public T populate(E e);
}
