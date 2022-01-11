package com.revature.models;

public enum ReimbursementType {
	LODGING {
		@Override
		public String toString() {
			return "Lodging";
		}
	},
	TRAVEL {
		@Override
		public String toString() {
			return "Travel";
		}
	},
	FOOD {
		@Override
		public String toString() {
			return "Food";
		}
	},
	OTHER {
		@Override
		public String toString() {
			return "Other";
		}
	};

}
