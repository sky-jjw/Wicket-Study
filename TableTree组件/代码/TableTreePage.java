package com.njupt.jjw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultTableTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ISortableTreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.Node;
import org.apache.wicket.extensions.markup.html.repeater.tree.table.NodeBorder;
import org.apache.wicket.extensions.markup.html.repeater.tree.table.NodeModel;
import org.apache.wicket.extensions.markup.html.repeater.tree.table.TreeColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

@SuppressWarnings("serial")
public class TableTreePage extends WebPage{
	
	private DefaultTableTree<Student,Void> studentTable;
	//模拟用户数据
	public static List<Student> students = new ArrayList<Student>();
	
	static {
		Student student1 = new Student("蒋");
		Student student2 = new Student("王");
		Student student3 = new Student("李");
		Student student4 = new Student("赵");
		Student student5 = new Student("魏");
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
	}
	
	private Student getStudent(String id) {
		return findStudents(students,id);
	}
	private Student findStudents(List<Student> students1, String id) {
		 for (Student student : students1)
	        {
	            if (student.getId().equals(id))
	            {
	                return student;
	            }
	        }
		return null;
	}
	
	private List<Student> getChild(Student student){
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("6"));
		list.add(new Student("7"));
		return list;
	}
	
	private boolean isHasChild(Student student) {
		if(student.getId().equals("蒋"))
			return true;
		return false;
	}
	
	public TableTreePage() {
		
		List<IColumn<Student, Void>> columns = new ArrayList<IColumn<Student,Void>>();
		
		columns.add(new TreeColumn<Student, Void>(Model.of("Tree")){

			@Override
			public void populateItem(Item<ICellPopulator<Student>> cellItem, String componentId,
					IModel<Student> rowModel) {
				NodeModel<Student> nodeModel = (NodeModel<Student>)rowModel;

				Node<Student> nodeComponent = new Node<Student>(componentId, getTree(), rowModel)
				{
					private static final long serialVersionUID = 1L;
					
					@Override
					protected Component createContent(String id, IModel<Student> model)
					{
						return new Label(id,model.getObject().getId());
					}
				};

				nodeComponent.add(new NodeBorder(nodeModel.getBranches()));

				cellItem.add(nodeComponent);
			}
			
		});
		
		columns.add(new PropertyColumn<Student,Void>(Model.of("ID"), "name"));
		
		ISortableTreeProvider<Student,Void> dataProvider = new ISortableTreeProvider<Student,Void>() {
			
			public IModel<Student> model(Student student) {
				return new StudentModel(student);
			}
			
			public Iterator<? extends Student> getRoots() {
				
				return students.iterator();
			}

			public Iterator<? extends Student> getChildren(Student student) {
				return getChild(student).iterator(); 
			}
			
			public boolean hasChildren(Student student) {
				return isHasChild(student);
			}

			public void detach() {
	
			}

			public ISortState<Void> getSortState() {
				return null;
			}

			
		};
		studentTable = new DefaultTableTree<Student, Void>("table", columns, dataProvider, 20);
		add(studentTable);

	}
	private class StudentModel extends LoadableDetachableModel<Student>{
		
		private final String id;
		
		public StudentModel(Student student) {
			super(student);
			id = student.getId();
		}

		@Override
		protected Student load() {
			return getStudent(id);
		}

		@Override
		public int hashCode() {
			return id.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			 if (obj instanceof StudentModel)
		        {
		            return ((StudentModel)obj).id.equals(id);
		        }
		        return false;
		}	
		
	}
	
}
