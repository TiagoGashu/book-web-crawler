<template>
<div class="nav-container" style="margin-bottom: -1px;">
	<ul class="nav nav-tabs">
		<li class="nav-item" @click="activate(item)" v-for="item in navigation.items">
			<a class="nav-link" :class="isActive(item)">
				{{item.name}}
			</a>
		</li>
	</ul>
</div>
</template>

<script>

export default {
	props: ['navigation'],
	methods: {
		activate(targetItem) {
			this.navigation.items.forEach((item) => {
				item.active = targetItem.name == item.name;
			});
		},
		isActive(item) {
			var classArr = []
			if(item.active) classArr.push('active')
			return classArr;
		}
	}
}
</script>
