package ec.com.tnb.mibus.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by f3r10 on 30/5/16.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
