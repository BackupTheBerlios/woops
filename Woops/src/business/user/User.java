package business.user;

import java.util.Collection;

public class User {

		/**
	 * @link aggregation 
	 * @associates business.activity.Activity
	 * @directed directed
	 * @supplierCardinality 0..*
	 */
	private Collection linkToActivity = null;

		public Collection getLinkToActivity() {
			return linkToActivity;
		}

		public void setLinkToActivity(Collection linkToActivity) {
			this.linkToActivity = linkToActivity;
		}
}
