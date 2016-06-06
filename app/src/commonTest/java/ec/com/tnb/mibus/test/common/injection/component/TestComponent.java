package ec.com.tnb.mibus.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import ec.com.tnb.mibus.injection.component.ApplicationComponent;
import ec.com.tnb.mibus.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
