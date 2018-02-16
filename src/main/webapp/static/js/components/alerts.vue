<template>
	<div class="alerts-container">
		<div v-for="alert in alerts" class="alert alert-dismissable" :class="classes(alert)">
			{{alert.msg}} <a class="close" @click="close(alert)">x</a>
		</div>
	</div>
</template>

<script>
	export default {
		props: ['alerts'],
		methods: {
			classes(alert) {
				var result = [];
				if(alert.success) result.push('alert-success');
				if(alert.error) result.push('alert-danger');
				return result;
			},
			close(alert) {
				this.alerts.splice(this.alerts.indexOf(alert), 1);
			}
		}
	}
</script>

<style>
	.alerts-container {
		position: absolute;
		bottom: 5px;
	    right: 0;
    	left: 0;
	}
	.alerts-container .close {
		float: right;
	}
</style>