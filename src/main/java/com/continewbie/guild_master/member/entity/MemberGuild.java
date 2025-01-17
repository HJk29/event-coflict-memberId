package com.continewbie.guild_master.member.entity;

import com.continewbie.guild_master.auditable.Auditable;
import com.continewbie.guild_master.guild.entity.Guild;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberGuild extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberGuildId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "GUILD_ID")
    private Guild guild;

    private String nickName;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<MemberGuildRole> memberGuildRoles = new ArrayList<>();

    public void addMember(Member member){
        this.member = member;
        if(!this.member.getMemberGuildList().contains(this)){
            this.member.addMemberGuild(this);
        }
    }

    public void addGuild(Guild guild){
        this.guild = guild;
        if(!this.guild.getMemberGuildList().contains(this)){
            this.guild.addMemberGuild(this);
        }
    }

    public enum MemberGuildRole {
        MEMBER_GUILD_ROLE_PLAYER(1, "일반 길드원"),
        MEMBER_GUILD_ROLE_MANAGER(2, "운영진"),
        MEMBER_GUILD_ROLE_MASTER(3, "길드 마스터");

        @Getter
        private int GuildRoleNumber;

        @Getter
        private String GuildRoleDescription;

        MemberGuildRole(int guildRoleNumber, String guildRoleDescription) {
            GuildRoleNumber = guildRoleNumber;
            GuildRoleDescription = guildRoleDescription;
        }
    }

}
