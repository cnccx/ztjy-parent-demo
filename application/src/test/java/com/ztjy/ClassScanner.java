package com.ztjy;


import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 
        * @Title 类扫描
        * @Description 扫描某路径下的所有类信息
		* @ClassName ClassScaner
		* @Copyright 2014-现在 厦门神州鹰掌通家园项目组
		* @author 胡耀忠 huyaozhong
		* @date 2018年5月3日 下午5:22:45 
 *
 */
public class ClassScanner implements ResourceLoaderAware {

	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	private final List<TypeFilter> includeFilters = new LinkedList<>();

	private final List<TypeFilter> excludeFilters = new LinkedList<>();

	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
			this.resourcePatternResolver);

	public ClassScanner() {

	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourcePatternResolver = ResourcePatternUtils
				.getResourcePatternResolver(resourceLoader);
		this.metadataReaderFactory = new CachingMetadataReaderFactory(
				resourceLoader);
	}

	public final ResourceLoader getResourceLoader() {
		return this.resourcePatternResolver;
	}

	public void addIncludeFilter(TypeFilter includeFilter) {
		this.includeFilters.add(includeFilter);
	}

	public void addExcludeFilter(TypeFilter excludeFilter) {
		this.excludeFilters.add(0, excludeFilter);
	}

	public void resetFilters(boolean useDefaultFilters) {
		this.includeFilters.clear();
		this.excludeFilters.clear();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set<Class> scan(String basePackage,
			Class<? extends Annotation>... annotations) {
		ClassScanner cs = new ClassScanner();
		for (Class anno : annotations){
			cs.addIncludeFilter(new AnnotationTypeFilter(anno));
		}
		return cs.doScan(basePackage);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set<Class> scan(String[] basePackages,
			Class<? extends Annotation>... annotations) {
		ClassScanner cs = new ClassScanner();
		for (Class anno : annotations){
			cs.addIncludeFilter(new AnnotationTypeFilter(anno));
		}
			
		Set<Class> classes = new HashSet<>();
		for (String s : basePackages){
			classes.addAll(cs.doScan(s));
		}
		return classes;
	}

	@SuppressWarnings("rawtypes")
	public Set<Class> doScan(String basePackage) {
		Set<Class> classes = new HashSet<>();
		try {
			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
					+ org.springframework.util.ClassUtils
							.convertClassNameToResourcePath(SystemPropertyUtils
									.resolvePlaceholders(basePackage))
					+ "/**/*.class";
			Resource[] resources = this.resourcePatternResolver
					.getResources(packageSearchPath);

			for (Resource resource : resources) {
				if (resource.isReadable()) {
					MetadataReader metadataReader = this.metadataReaderFactory
							.getMetadataReader(resource);
					boolean b = (includeFilters.size() == 0 && excludeFilters.size() == 0) || matches(metadataReader);
					if (b) {
						try {
							classes.add(Class.forName(metadataReader
									.getClassMetadata().getClassName()));
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

					}
				}
			}
		} catch (IOException ex) {
			throw new BeanDefinitionStoreException(
					"I/O failure during classpath scanning", ex);
		}
		return classes;
	}

	private boolean matches(MetadataReader metadataReader) throws IOException {
		for (TypeFilter tf : this.excludeFilters) {
			if (tf.match(metadataReader, this.metadataReaderFactory)) {
				return false;
			}
		}
		for (TypeFilter tf : this.includeFilters) {
			if (tf.match(metadataReader, this.metadataReaderFactory)) {
				return true;
			}
		}
		return false;
	}

}



