<template>
    <div>
        <h4>유저 목록</h4>
        <hr>
        <div class = "row">
            <div v-for="user in UserBoardList" :key="userboard.id">
            <div>
                <img :src="user.profile_img" alt="Thumbnail">
                <div>
                    <h5 @click="showUserBoardDetail(userboard.user_id)">
                        <span>{{ userboard.user.nickname }}</span>
                    </h5>
                </div>
            </div>
            
            </div>
        </div>

    </div>
</template>

<script setup>

</script>

<style scoped>

</style>