// @ts-ignore
/* eslint-disable */

declare namespace API {

  /**
   * 通用返回类型
   */
  type BaseResponse<T> = {
    code: number,
    data: T,
    message: string,
    description?: string
  }

  type CurrentUser = {
    id: number;
    userName: string;
    gender: string;
    phone: string;
    email: string;
    userStatus: number;
    crateTime: Date;
    updateTime: Date;
    userRole: number;
    avatarUrl?: string;
    userAccount?: string;
    userPassword?: string;
  };

  type LoginResult = {
    id: number;
    userName: string;
    gender: string;
    phone: string;
    email: string;
    userStatus: number;
    userRole: string;
    avatarUrl?: string;
    userAccount?: string;
  };

  type RegisterResult = number;

  type PageParams = {
    current?: number;
    pageSize?: number;
  };

  type RuleListItem = {
    key?: number;
    disabled?: boolean;
    href?: string;
    avatar?: string;
    name?: string;
    owner?: string;
    desc?: string;
    callNo?: number;
    status?: number;
    updatedAt?: string;
    createdAt?: string;
    progress?: number;
  };

  type UserList = {
    data?: CurrentUser[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type RuleList = {
    data?: RuleListItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type FakeCaptcha = {
    code?: number;
    status?: string;
  };

  type LoginParams = {
    userAccount?: string;
    userPassword?: string;
    autoLogin?: boolean;
    type?: string;
  };

  type RegisterParams = {
    userAccount?: string;
    userPassword?: string;
    checkPassword?: string;
    type?: string;
  };

  type ErrorResponse = {
    /** 业务约定的错误码 */
    errorCode: string;
    /** 业务上的错误信息 */
    errorMessage?: string;
    /** 业务上的请求是否成功 */
    success?: boolean;
  };

  type NoticeIconList = {
    data?: NoticeIconItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type NoticeIconItemType = 'notification' | 'message' | 'event';

  type NoticeIconItem = {
    id?: string;
    extra?: string;
    key?: string;
    read?: boolean;
    avatar?: string;
    title?: string;
    status?: string;
    datetime?: string;
    description?: string;
    type?: NoticeIconItemType;
  };
}
