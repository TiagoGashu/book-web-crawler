<template>
<div class="edit-manga">

	<nav-tabs :navigation="navigation"></nav-tabs>

	<div v-show="isActive('Info')">
		<!-- source -->
		<div class="input-group">
			<div class="input-group-prepend mb-3" title="o site fonte do manga">
				<span class="input-group-text">Source</span>
			</div>
			<input v-model="manga.source" type="text" class="form-control" style="height: 38px;" />
			<div class="input-group-append" title="load from source" @click="showConfirmLoadFromSource" style="height: 38px;">
				<i class="material-icons load-from-source-btn" style="line-height: 38px;">get_app</i>
			</div>
		</div>
	
		<!-- generico de um manga -->
		<div class="editing-container">
			<book :book="manga"></book>
		</div>
	</div>

	<div v-show="isActive('Chapters')">
		<!-- chapters -->
		<div v-for="chapter in manga.mangaChapters" class="editing-container">
			<div class="input-group">
				<div class="input-group-prepend mb-3">
					<span class="input-group-text">Chapter</span>
				</div>
				<input v-model="chapter.chapterName" type="text" class="form-control" style="height: 38px;"/>
			</div>
			<div class="input-group">
				<div class="input-group-prepend mb-3">
					<span class="input-group-text">Source</span>
				</div>
				<input v-model="chapter.source" type="text" class="form-control" style="height: 38px;"/>
			</div>
			<div class="">
				<span @click="removeChapter(chapter)">
					<i class="material-icons">delete_sweep</i>	
				</span>
			</div>
		</div>
		<div>
			<!-- add chapter btn -->
	     	<div class="add-button">
	     		<button class="btn btn-primary btn-block" @click="addChapter">add chapter</button>
	     	</div>
		</div>
	</div>
</div>
</template>

<script>
	import store from '../vuex/store.vue'
	
	var modalConfirmSource = {
		bodyMessage: 'Load info from source?',
		cancelCallback: function() { /** nope */ }
	}
	
	var navigation = {
		items: [
			{name: 'Info', active: true},
			{name: 'Chapters', active: false}
		],
		getActiveItem() {
			var actives = this.items.filter((i) => i.active);
			if(actives && actives.length == 1) return actives[0];
			return null;
		}
	}
	
	var data = {
		modalConfirmSource,
		navigation
	}
	
	export default {
		data() {
			return data;
		},
		props: ['manga'],
		methods: {
			isActive(sectionName) {
				return this.navigation.getActiveItem().name == sectionName;
			},
			showConfirmLoadFromSource() {
				if(!this.manga.source) {
					return;
				}
				this.$store.dispatch('showModalConfirm', this.modalConfirmSource);
			},
			loadFromSource() {
				if(!this.manga.source) {
					return;
				}
				this.$store.dispatch('loadMangaInfoFromSource', this.manga)
			},
			addChapter() {
				this.manga.genres.push({genre: ''});
			},
			removeChapter(chapter) {
				this.manga.mangaChapters.splice(this.manga.mangaChapters.indexOf(chapter), 1);
			}
		},
		mounted() {
			this.modalConfirmSource.confirmCallback = this.loadFromSource;
		}
	}
</script>

<style>
	.load-from-source-btn {
		cursor: pointer;
	}
</style>
