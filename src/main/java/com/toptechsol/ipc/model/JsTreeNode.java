package com.toptechsol.ipc.model;

import java.util.ArrayList;

public class JsTreeNode {

	private String id;
	private String text;
	private String icon;
	private State state;
	private String color;
	private boolean notAllowedToEdit;
	private String description;

	private ArrayList<JsTreeNode> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public ArrayList<JsTreeNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<JsTreeNode> children) {
		this.children = children;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isNotAllowedToEdit() {
		return notAllowedToEdit;
	}

	public void setNotAllowedToEdit(boolean notAllowedToEdit) {
		this.notAllowedToEdit = notAllowedToEdit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
