package com.njupt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

@SuppressWarnings("serial")
public class FileUploadExample extends WebPage {

	private List<FileUpload> uploads;

	//在D盘创建文件，文件中存储学生的基本信息（模拟数据库）
	private File getStudentInfosFile() {
		return new File("D:\\javaExample\\studentInfos.ser");
	}
	//从本地读取文件
	@SuppressWarnings("unchecked")
	private Map<String, Student> readStudentInfos() {
		File file = getStudentInfosFile();
		Map<String, Student> studentMap = new HashMap<String,Student>();
		if (file.exists()) {
			try {
				byte[] bytes = FileUtils.readFileToByteArray(file);
				ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bis);
				ois.close();
				bis.close();
				studentMap = (Map<String, Student>) ois.readObject();
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			return studentMap;
		}else {
			return new HashMap<>();
		}
	}

	@Override
	protected void onInitialize() {
		// TODO Auto-generated method stub
		super.onInitialize();
		final Form<?> form = new Form<Object>("form");
		//文件上传组件
		FileUploadField field = new FileUploadField("upload", new IModel<List<FileUpload>>() {

			public List<FileUpload> getObject() {
				// TODO Auto-generated method stub
				return uploads;
			}

			public void setObject(List<FileUpload> object) {
				// TODO Auto-generated method stub
				uploads = object;
			}

		});
		field.setOutputMarkupId(true);
		form.add(field);
		//处理文件上传的操作，把文件中的内容赋给Student类
		form.add(new Button("showStudentInfo") {
			@Override
			public void onSubmit() {
				Map<String, Student> studentInfos = readStudentInfos();
				if (uploads == null || uploads.isEmpty()) {
					form.error("请选择文件");
				} else {
					try (InputStream is = uploads.get(0).getInputStream()) {
						for (String line : IOUtils.readLines(is, "utf-8")) {
							
							String[] fields = line.split(",");

							if (fields.length != 4)
								form.error("文件格式不正确");
							Student student = new Student();
							student.setStudentNumber(fields[0]);
							student.setStudentName(fields[1]);
							student.setStudentEmail(fields[2]);
							student.setStudentPhone(fields[3]);
							studentInfos.put(student.getStudentNumber(), student);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					//把对象写入文件中
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					try {
						ObjectOutputStream oos = new ObjectOutputStream(bos);
						oos.writeObject(studentInfos);
						oos.flush();
						byte[] bytes = bos.toByteArray();
						FileUtils.writeByteArrayToFile(getStudentInfosFile(), bytes);
						oos.close();
						bos.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		//提示面板
		form.add(new FeedbackPanel("feedback"));
		//Table的列
		List<IColumn<Student, Void>> columns = new ArrayList<>();
		columns.add(new PropertyColumn<Student, Void>(Model.of("学号"), "studentNumber"));
		columns.add(new PropertyColumn<Student, Void>(Model.of("姓名"), "studentName"));
		columns.add(new PropertyColumn<Student, Void>(Model.of("邮箱"), "studentEmail"));
		columns.add(new PropertyColumn<Student, Void>(Model.of("手机号"), "studentPhone"));
		//数据
		SortableDataProvider<Student, Void> dataProvider = new SortableDataProvider<Student, Void>() {

			@Override
			public Iterator<? extends Student> iterator(long first, long count) {
				List<Student> keys = new ArrayList<>(readStudentInfos().values());
				if (first >= readStudentInfos().size())
					return keys.subList(keys.size(), keys.size()).iterator();
				else if (first + count > keys.size())
					return keys.subList((int) first, keys.size()).iterator();
				else
					return keys.subList((int) first, (int) (first + count)).iterator();
			}

			@Override
			public IModel<Student> model(Student student) {
				// TODO Auto-generated method stub
				return Model.of(student);
			}

			@Override
			public long size() {
				// TODO Auto-generated method stub
				return readStudentInfos().size();
			}
		};
		DefaultDataTable<Student, Void> studentDataTable = new DefaultDataTable<Student, Void>("studentsTable", columns,
				dataProvider, 12);
		form.add(studentDataTable);
		add(form);

	}

}
